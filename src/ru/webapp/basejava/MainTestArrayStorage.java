package ru.webapp.basejava;

import ru.webapp.basejava.model.Resume;
import ru.webapp.basejava.storage.ArrayStorage;
import ru.webapp.basejava.storage.IStorage;
import ru.webapp.basejava.storage.SortedArrayStorage;

/**
 * Test for your ru.webapp.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final IStorage ARRAY_STORAGE = new ArrayStorage();
    static final IStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid2");
        Resume r2 = new Resume("uuid3");
        Resume r3 = new Resume("uuid1");

        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);

        printAll();

        System.out.println("Get r1: " + SORTED_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + SORTED_ARRAY_STORAGE.get("dummy"));

        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        SORTED_ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

//        ARRAY_STORAGE.save(r1);
//        ARRAY_STORAGE.save(r2);
//        ARRAY_STORAGE.save(r3);
//
//        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

//        printAll();
//        ARRAY_STORAGE.delete(r1.getUuid());
//        printAll();
//        ARRAY_STORAGE.clear();
//        printAll();
//
//        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
