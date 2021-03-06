### Call By Value(값에 의한 호출)
함수로 인자를 전달할 때 전달 될 인자를 `복사`하며, 복사된 값은 함수 내에서 `지역적으로 사용`되는 특징을 가지고 있다. Caller는 인자값을 복사 방식으로 넘겨 주었으므로 Callee 내에서 어떤 작업을 하더라도 *Caller는 영향을 받지 않는다.* 즉, 다른 메모리에 각각 저장되고, 함수가 종료됨과 동시에 함수의 변수는 소멸하게 되어 서로 직접적인 연관이 없다.
하지만 인자를 전달할 때마다 메모리의 공간을 할당하여 메모리 공간에 대한 문제점이 있을 수 있다.

### Call By Reference(참조에 의한 호출)
인자로 사용될 변수의 레퍼런스를 전달한다. 즉 x, y를 전달할 경우 해당 변수의 값을 전달하는 것이 아닌 x, y 변수 자체에 대한 레퍼런스를 전달하는 것이다.
메모리 공간 할당의 문제를 벗어나게 되지만 원본의 데이터를 훼손할 수 있다는 문제점이 있다.

---

**자바**의 자료형은 `기본 자료형`과 `참조 자료형`이 있다.
기본 자료형에 해당하는 변수를 인자로 넘길 때는 값(value)를 넘기고, 참조 자료형인 변수를 인자로 넘기면 해당 변수가 가진 값(value)이 레퍼런스이므로 레퍼런스가 전달되는 것이다. 아래 예시를 살펴보자.

```java
class Update {
    public void call_by_val(int count) {
        count++;
    }
    public void call_by_ref(CallBy cb) {
        // 실행 메서드 클래스의 주소 값을 인자로 전달 받고, 같은 메모리 내 'count'의 값을 변경 
        cb.count++;
    }
}

// 실행 메서드가 위치
class CallBy {
    int count = 0;
    public static void main(String[] args) {
        CallBy cb = new CallBy();
        Update = update = new Update();
        
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
```

결론적으로 두 메서드가 모두 Call By Value이지만 참조 자료형을 넘겼을 경우, 해당 객체의 메모리 주소 값을 넘겨서 메서드가 인자로 넘긴 객체 내부 멤버에 영향력을 가하게 되므로 Call By Reference처럼 보이는 것이다.

### Call By Assignment(assignment에 의한 함수 호출 방식)
**파이썬**에서 모든 것은 객체이고 객체에는 `Immutable Object, Mutable Object` 2가지 종류가 있다. (파이썬은 call by reference도 아니고 call by value도 아니다.)

<p class="quote">
	<b>Immutable Object</b> <br />
	- int, float, str, tuple <br />
	- 처음에는 무조건 reference처럼 (받은 인자의 주소 값을)가리키고 있지만 값이 변경되면, 내부 공간에 별도로 공간을 할당하여 해당 메모리를 가리킨다. <br />
	- 동작이 끝나면 메서드 공간이 사라진다. (Call By Value와 유사) <br />
	- 즉, 함수 내에서 formal parameter 값이 바뀌어도 actual parameter에는 영향이 없다. <br /><br />

	<b>Mutable Object</b> <br />
	- list, dict, set <br />
	- 인자로 object reference가 전달되어 actual parameter의 값에 영향을 미친다. 
</p>

아래 예제를 살펴보자.

```python
def val(a):
    a = 'new value'
    print("메서드 내 a의 id", id(a))
    
a = 'old_value'
val(a)
print("메서드 실행 전 a의 id", id(a))
"메서드 실행 후", a

# OutPut
# 메서드 내 값 변경전 a의 id 4368663920
# 메서드 내 값 변경후 a의 id 4396828528
# 메서드 실행 전 a의 id 4368663920
# Out[3]: ('메서드 실행 후', 'old_value') # 값이 안 바뀜
    
def ref(b):
    b[0] = 'new value1' # 변경
    print("메서드 내 변경된 b의 id", id(b))
    b = ['new value2'] # 새로 할당
    print("메서드 내 새로 할당한 b의 id", id(b))

b = ['old value']
val(b)
print("메서드 실행 전 b의 id", id(b))
"메서드 실행 후", b

# OutPut
# 메서드 내 변경된 b의 id 4523957704
# 메서드 내 새로 할당한 b의 id 4523955592
# 메서드 실행 전 b의 id 4523957704
# Out[4]: ('메서드 실행 후', ['new value1']) # 값이 바뀌나 새로 할당한 값은 적용되지 않음
```

- 파이썬에는 builtin 함수로 `id`라는 것이 있다. 이는 해당 변수의 Pointer 비슷한 것을 가지고 있는데, id가 같은지를 검사하는 연산자로 `is`가 있다.
- val() 메서드의 경우 str을 인자로 넘겼으며, 넘기기 전 a의 id 값과 넘긴 후 a의 id 값은 같지만(같은 주소 값을 가리키지만), `변경후` a의 id 값이 달라지는 것을 확인할 수 있다.
- ref() 메서드의 경우 list를 인자로 넘겼으며, 넘기기 전 b의 id 값과 넘긴 후 `변경`된 b의 id 값이 같지만, `새로 할당`하게 되면 b의 id 값이 달라지는 것을 확인할 수 있다. 