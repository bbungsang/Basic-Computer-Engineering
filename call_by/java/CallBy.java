class Update {
    public void call_by_val(int count) {
        count++;
    }
    public void call_by_ref(CallBy cb) {
        cb.count++;
    }
}

class CallBy {
    int count = 0;
    public static void main(String[] args) {
        CallBy cb = new CallBy();
        Update update = new Update();

        // Call By Value
        update.call_by_val(cb.count);
        System.out.println(String.format("%s %d", "[Call By Value] count:", cb.count));

        // Call By Reference 
        update.call_by_ref(cb);
        System.out.println(String.format("%s %d", "[Call By Reference] count:", cb.count));
    }
}

// OutPut
// [Call By Value] count: 0
// [Call By Reference] count: 1
