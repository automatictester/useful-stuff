package uk.co.automatictester.state;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class LockableMap<K, V> {

    private LockableMapState<K, V> state = new UnlockedMap<>(this);
    protected Map<K, V> map = new HashMap<>();

    public void lock() {
        state = new LockedMap<>(this);
    }

    public void unlock() {
        state = new UnlockedMap<>(this);
    }

    public void put(K key, V value) {
        state.put(key, value);
    }

    public V get(K key) {
        return state.get(key);
    }
}
