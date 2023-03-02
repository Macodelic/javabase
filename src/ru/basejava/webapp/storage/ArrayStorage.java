package ru.basejava.webapp.storage;

import ru.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

//    public void update(Resume resume) {
//        int resumeIndex = findIndex(resume.getUuid();
//        if (resumeIndex != -1) {
//            //???
//        } else {
//            System.out.println("Резюме не найдено " + resume);
//        }
//    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Хранилище переполнено");
            return;
        }
        int resumeIndex = findIndex(resume.getUuid());
        if (resumeIndex == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("Резюме " + resume + " уже находится в хранилище");
        }

    }

    public Resume get(String uuid) {
        int resumeIndex = findIndex(uuid);
        if (resumeIndex != -1) {
            return storage[resumeIndex];
        } else {
            System.out.println("Резюме не найдено " + uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = findIndex(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме не найдено " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
