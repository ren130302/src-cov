
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start");

		final List<String> srcMappingList = new ArrayList<>();

		//		csvFilePathList().forEach(csvFile -> new Thread(() -> srcMappingList.addAll(srcMappingList(csvFile))));
		//		javaFilePathList().forEach(javaFile -> new Thread(() -> replace(javaFile, srcMappingList)).start());
		csvFilePathList().forEach(csvFile -> new Thread(() -> srcMappingList.addAll(srcMappingList(csvFile))).start());
		javaFilePathList().forEach(javaFile -> new Thread(() -> replace(javaFile, srcMappingList)).start());
	}

	public static List<Path> javaFilePathList() {
		final List<Path> javaFileList = new ArrayList<Path>();
		final Path rootFolder = Paths.get("files");
		reentrantJavaFile(javaFileList, rootFolder);
		return javaFileList;
	}

	public static void reentrantJavaFile(List<Path> javaFileList, Path folder) {
		try {
			if (folder.toString().endsWith(".java")) {
				javaFileList.add(folder);
			} else if (Files.isDirectory(folder)) {
				for (Path file : Files.list(folder).collect(Collectors.toList())) {
					reentrantJavaFile(javaFileList, file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void replace(Path folder, List<String> srcMappingList) {
		System.out.println("[Reentrant]Read Java File -> " + folder);
		try {
			List<String> replacedLines = Files.readAllLines(folder);

			for (String srcMapping : srcMappingList) {
				final String[] src = srcMapping.split(",");
				replacedLines = replacedLines.stream()
						.map(line -> line.replaceAll(src[0], src[1]))
						.collect(Collectors.toList());
			}

			Files.write(folder, replacedLines, StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Path> csvFilePathList() {
		final Path csvFolder = Paths.get("csv");
		final List<Path> csvFilePathList = new ArrayList<>();
		try {
			Files.list(csvFolder).forEach(csvFilePathList::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvFilePathList;
	}

	public static List<String> srcMappingList(Path csvFile) {
		final List<String> srcMappingList = new ArrayList<>();

		if (csvFile.toString().endsWith(".csv")) {
			System.out.println("[SrcMapping]Read CSV File Start -> " + csvFile);

			try {
				Files.readAllLines(csvFile).forEach(srcMappingList::add);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.currentThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[SrcMapping]Read CSV File End -> " + csvFile);
		}

		return srcMappingList;
	}
}