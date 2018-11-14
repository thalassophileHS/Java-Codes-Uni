package space.harbour.java.hw3;

public class HashMap {

    private static final int SIZE = 16;
    private Entry table[] = new Entry[SIZE];

    class Entry {

        Book key;
        String value;
        Entry next;

        Entry(Book k, String v) {
            key = k;
            value = v;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Book getKey() {
            return key;
        }
    }


    private int getSupplementalHash(int h) {

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int getBucketNumber(int hash) {
        return hash & (SIZE - 1);
    }

    public void put(Book key, String value) {

        int userHash = key.hashCode();
        int hash = getSupplementalHash(userHash);
        int bucket = getBucketNumber(hash);
        Entry existingElement = table[bucket];

        for (; existingElement != null; existingElement = existingElement.next) {

            if (existingElement.key.equals(key)) {
                System.out
                        .println("duplicate key value pair, replacing existing key "
                                + key + ", with value " + value);
                existingElement.value = value;
                return;
            } else {
                System.out.println("Collision detected for key " + key
                        + ", adding element to the existing bucket");

            }
        }

        //
        System.out.println("PUT adding key:" + key + ", value:" + value
                + " to the list");
        Entry entryInOldBucket = new Entry(key, value);
        entryInOldBucket.next = table[bucket];
        table[bucket] = entryInOldBucket;
    }

    public Entry get(Book key) {

        int hash = getSupplementalHash(key.hashCode());

        int bucket = getBucketNumber(hash);

        Entry existingElement = table[bucket];

        while (existingElement != null) {
            System.out
                    .println("Traversing the list inside the bucket for the key "
                            + existingElement.getKey());
            if (existingElement.key.equals(key)) {
                return existingElement;
            }
            existingElement = existingElement.next;
        }

        return null;
    }

    // for testing map
    public static void main(String[] args) {
        HashMap tmhm = new HashMap();

    }

    static class Book {

        private Integer id;
        private String name;

        Book(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode(){
            return id % 10;
        }

        @Override
        public boolean equals(Object obj) {
            Book otherBook = (Book) obj;
            return this.name.equals(otherBook.name);
        }

        @Override
        public String toString() {
            return this.id + "-" + name;
        }
    }

}