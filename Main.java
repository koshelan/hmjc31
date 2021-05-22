package hmjc.hm3.hm31;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuffer pathToDir = new StringBuffer("C:\\javahm\\Games");
        StringBuffer logs = new StringBuffer();
        boolean installationFinished = true;

        File root = new File(pathToDir.toString());
        File srcDir = new File(root, "src");
        File resDir = new File(root, "res");
        File savegamesDir = new File(root, "savegames");
        File tempDir = new File(root, "temp");
        File srcMainDir = new File(srcDir, "main");
        File srcMTestDir = new File(srcDir, "test");
        File srcMainMainFile = new File(srcMainDir, "Main.java");
        File srcMainUtilsFile = new File(srcMainDir, "Utils.java");
        File resDrawablesDir = new File(resDir, "drawables");
        File resVectorsDir = new File(resDir, "vectors");
        File resIconsDir = new File(resDir, "icons");
        File tempTempFile = new File(tempDir, "temp.txt");

        logs.append("Установка начата\n");
        logs.append("Каталоги сохраняются в папку: ")
                .append(pathToDir)
                .append("\n");
        if (root.exists()) {
            installationFinished = makeDir(srcDir, logs)
                    & makeDir(resDir, logs)
                    & makeDir(savegamesDir, logs)
                    & makeDir(tempDir, logs)
                    & makeDir(srcMainDir, logs)
                    & makeDir(srcMTestDir, logs)
                    & makeFile(srcMainMainFile, logs)
                    & makeFile(srcMainUtilsFile, logs)
                    & makeDir(resDrawablesDir, logs)
                    & makeDir(resVectorsDir, logs)
                    & makeDir(resIconsDir, logs)
                    & makeFile(tempTempFile, logs);
            if (tempTempFile.exists()) {

                logs.append("Попытка записи логов в файл ").append(tempTempFile.getName())
                        .append(" в папке ").append(tempTempFile.getPath()).append("\n");

                try (FileWriter writer = new FileWriter(tempTempFile)) {
                    writer.write(logs.toString());
                    writer.append("Запись логов прошла успешна\n");
                    logs.append("Запись логов прошла успешна\n");
                    if (installationFinished) {
                        writer.append("Установка прошла успешно");
                        logs.append("Установка прошла успешно");
                    } else {
                        writer.append("Установка прошла НЕ успешно. что-то пошла не так. попробуйте снова");
                        logs.append("Установка прошла НЕ успешно. что-то пошла не так. попробуйте снова");
                    }
                    writer.flush();
                } catch (IOException ex) {
                    logs.append("Прошла НЕ успешно! Ошибка:\n");
                    logs.append(ex.getMessage()).append("\n");
                    installationFinished = false;
                }

            } else {
                installationFinished = false;
                logs.append("Файл ").append(tempTempFile.getName()).append(" в папке ")
                        .append(tempTempFile.getPath()).append(" НЕ существует! Ошибка:\n");
            }

        } else {
            logs.append("Папка не существует! Уточните путь. Ошибка\n")
                    .append("Установка прошла НЕ успешно. что-то пошла не так. попробуйте снова");
            installationFinished = false;
        }

        if (installationFinished) {
            System.out.println("установка проша успешно");
        } else {
            System.out.println(logs);

        }
    }

    static boolean makeDir(File dir, StringBuffer logs) {
        if (dir.mkdir()) {
            logs.append("Папка ").append(dir.getPath()).append(" созадана успешно\n");
            return true;
        } else {
            logs.append("Папка ").append(dir.getPath()).append(" НЕ создана! Ошибка!\n");
            return false;
        }
    }

    static boolean makeFile(File file, StringBuffer logs) {

        try {
            if (file.createNewFile()) {
                logs.append("Файл ").append(file.getName()).append(" в папке ")
                        .append(file.getPath()).append(" созадан успешно\n");
                return true;
            } else {
                logs.append("Файл ").append(file.getName()).append(" в папке ")
                        .append(file.getPath()).append(" НЕ создан! Ошибка!\n");
                return false;
            }
        } catch (IOException ex) {
            logs.append("Файл ").append(file.getName()).append(" в папке ")
                    .append(file.getPath()).append(" НЕ создан! Ошибка:\n");
            logs.append(ex.getMessage()).append("\n");
            return false;
        }

    }


}
