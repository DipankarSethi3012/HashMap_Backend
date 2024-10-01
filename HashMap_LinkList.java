import java.util.*;
class HashMapFinal<K, V> {

    ArrayList<LinkedList<Entity>> list;
    private int size = 0;
    private float loadFactor = 0.5f;

    public HashMapFinal() {
        list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities= list.get(hash);

        for(Entity entity : entities) {
            if(entity.key.equals(key)) {
                entity.value = value;
                return;
            }
        }

        if((float)(size) / list.size() > loadFactor) {
            reHash();
        }
        entities.add(new Entity(key, value)); //adding new entity to that link list present at that hash of key
        size++;
    }

    private void reHash() {
        System.out.println("map is full..Now rehashing");
        ArrayList<LinkedList<Entity>> old = list;
        list = new ArrayList<>();
        size = 0;

        for(int i = 0; i < old.size() * 2; i++) {
            list.add(new LinkedList<>());
        }

        for(LinkedList<Entity> entries : old) {
            for(Entity entity : entries) {
                put(entity.key, entity.value);
            }
        }
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities = list.get(hash);

        for(Entity entity : entities) {
            if(entity.key.equals(key)) {
                return entity.value;
            }
        }

        return null;
    }

    public void remove(K key) {
        int hash = Math.abs(key.hashCode() % list.size());
        LinkedList<Entity> entities = list.get(hash);

        Entity target = null;

        for(Entity entity : entities) {
            if(entity.key.equals(key)) {
                target = entity;
                break;
            }
        }

        entities.remove(target);
        size--;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }


    private class Entity{
        K key;
        V value;

       public Entity(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
public class HashMap_LinkList{
    public static void main(String[] args) {
     HashMapFinal<String, Integer> mp = new HashMapFinal<>();
     mp.put("abc", 1);
        System.out.println(mp.get("abc"));
    }
}
