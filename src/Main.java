
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
	final static List<String> srcMappingList = new ArrayList<>();
	final static List<Path> javaFileList = new ArrayList<Path>();

	final static Map<Path, List<String>> datas = new HashMap<>();

	final static ExecutorService executor = Executors.newCachedThreadPool();

	static int complete = 0;

	public static void main(String[] args) throws InterruptedException {
		Future<?> csvFuture = executor.submit(Main::csvFile);
		Future<?> javaFuture = executor.submit(Main::javaFile);

		while (true) {
			if (csvFuture.isDone() && javaFuture.isDone()) {
				break;
			}
		}

		System.out.println("[ReplaceSrc]Start ");
		Future<?> readJavaFileFuture = executor.submit(Main::readJavaFile);

		while (true) {
			if (readJavaFileFuture.isDone()) {
				break;
			}
		}

		for (Path path : datas.keySet()) {
			executor.submit(() -> replaceSrc(path));
		}

		executor.shutdown();
		executor.awaitTermination(0, TimeUnit.MICROSECONDS);
	}

	private static void csvFile() {
		{
			try {
				Files.list(Paths.get("csv")).collect(Collectors.toList()).forEach(csvFile -> {
					if (csvFile.toString().endsWith(".csv")) {
						try {
							System.out.println("[SrcMapping]Read CSV File Start -> " + csvFile);
							Files.readAllLines(csvFile).forEach(srcMappingList::add);
							System.out.println("[SrcMapping]Read CSV File End -> " + csvFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void javaFile() {
		reentrantJavaFile(Paths.get("files"));
	}

	private static void reentrantJavaFile(Path folder) {
		try {
			if (folder.toString().endsWith(".java")) {
				javaFileList.add(folder);
			} else if (Files.isDirectory(folder)) {
				Files.list(folder).parallel().forEach(Main::reentrantJavaFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readJavaFile() {
		datas.putAll(javaFileList.parallelStream().collect(Collectors.toMap(path -> path, path -> {
			try {
				return Files.readAllLines(path);
			} catch (IOException e) {
				throw new NullPointerException();
			}
		})));
		javaFileList.forEach(t -> {
			try {
				Files.deleteIfExists(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static void replaceSrc(Path path) {
		try {
			System.out.println("[ReplaceSrc]Read Java File -> " + path);

			final List<String> readAllLines = datas.get(path);

			final List<String> stream = readAllLines.parallelStream().map(line -> {
				String tempLine = line;

				for (String srcMapping : srcMappingList) {
					final String[] src = srcMapping.split(",");
					tempLine = tempLine.replaceAll(src[0], src[1]);
				}

				return tempLine;
			}).collect(Collectors.toList());

			if (readAllLines.size() != stream.size()) {
				System.err.println("[ReplaceSrc]Error -> " + path);
			}

			System.out.println("[ReplaceSrc]Write Java File -> " + path);
			Files.write(path, stream, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

			complete++;
			System.out.println("[ReplaceSrc]Tasks -> " + complete + "/" + javaFileList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}