## 자료구조와 알고리즘
### 자료구조는 왜 배우는가?
단도직입적으로 면접관이 물어보기 때문에, 상사가 전달하는 말을 얼마나 이해하고 잘 구현해내느냐, 프로그래머로서의 자질을 판단하기 위해

### 자료구조
- 상황에 따라 데이터를 어떤 구조로 `저장`, `탐색`, `삭제` 해야 가장 효율적인가? 자료의 구조 자체
- 상황마다 적합한 자료구조가 다르다.

### 알고리즘
- 문제를 해결하는 방법론, 한 문제를 해결하는 방법이 여러가지가 있는데 가장 빠르고 효율적인 방법이 무엇인지를 탐색

### 자료구조의 알고리즘
: 데이터를 저장하고 탐색하는 방법에 대한 고민들

### 자료구조를 이용한 알고리즘
: 자료구조를 이용해 어떤 문제를 해결하는 것
<br><br>

## 선형 자료 구조
### 배열(array)
- **동일한** 자료형을 가진 변수들의 모임 (!= 파이썬 리스트)
- 일단 선언하면 크기가 고정, 절대 변하지 않음
- 만약 4개의 배열을 늘리고 싶다면, 6개를 할당할 수 있는 공간을 찾고, 복사한 후, 기존 배열을 삭제해야 한다. 어마어마한 리소스 낭비!
- 3 을 찾고 싶다면 인덱싱으로 한방에 탐색할 수 있다. 따라서 데이터 접근이 빈번할 때 배열을 사용한다.
- 한 페이지에 순차적으로 할당한다.
- stack 에 할당, 메보리 공간 보장

### 링크드리스트
- 지우거나 복사하거나 할당할 필요없이 기존 데이터에서 가리키는 메모리 방향만 바꾸면 된다.
- 3 을 찾고 싶다면, 가리키는 방향을 타고 타고해서 참조를 통해서 찾아야 한다.
- 데이터를 자주 추가하고 삭제하고 싶을 때 사용한다.
- heap 에 랜덤으로 할당, 여러 페이지에 메모리가 뒤죽박죽 배정되어 있을 경우, 메모리를 삭제하고 올리는데 굉장한 낭비를 초래한다.
<br><br>

> 짚고 넘어가기 : 파이썬 함수는 객체 다른 언어의 경우 함수는 객체가 아니다!
OOP 에서 파생되어 나온 인스턴스이다.

## Recursion Function
큰 상자 안에 작은 상자, 작은 상자 안에 더 작은 상자, 이런 상자들 속에 하나의 열쇠가 있다. 열쇠를 찾는 알고리즘을 구상해보자.

\#1 상자 안의 물건을 확인한다. <br>
\#2 만약 상자를 발견하면 다시 \#1 로 <br>
\#3 열쇠를 발견하면 작업 종료!

```python
def look_for_key(box):
    for item in box:
        if item.is_a_box():
            look_for_key(item) # ➜ 자기 호출 : 재귀함수
        elif item.is_a_key():
            print("열쇠를 찾았다!")
```

다음은 재귀로 팩토리얼을 표현한 것이다.

```python
def factorial(n):
    return n * factorial(n-1)

if __name__ == "__main__":
    n = 100
    print(factorial(n))
```
- 이 경우, 함수가 끝없이 실행되어 stack over flow 를 발생시킨다. 
- 따라서 재귀함수는 언제 재귀를 멈출지 알려주는 `탈출 조건`이 필요하다.

팩토리얼 `0! = 1!` 의 성질을 활용하여, 탈출조건을 추가해보자.

```python
def factorial(n):
    # 탈출 조건
    if n <= 1:
        return 1
    return n * factorial(n-1)

if __name__ == "__main__":
    n = 5
    print(factorial(n))
    
# result : 120
```

다음은 재귀 함수를 활용하여 피보나치 수열을 표현한 것이다.

```python
def fibo(n):
    if n== 0 or n == 1:
        return 1
    return fibo(n-2) + fibo(n-1)
fibo(5)

# result : 8
```

### 제너레이터
- generator 는 iterator 를 생성해 주는 function 이다. iterator 는 next() 메소드를 이용해 데이터에 `순차적으로 접근`이 가능한 object 이다.
- yield 는 generator 가 일반 함수와 구분되는 가장 핵심적인 부분이다.

#### yield
- 일반적인 함수는 사용이 종료되면 결과값을 호출부로 반환 후 함수 자체를 종료시킨 후 메모리 상에서 클리어된다.
- 하지만 yield 의 경우, 해당 함수는 `그 상태로 정지` 되며, 반환 값을 `next()` 를 호출한 쪽으로 전달하게 된다. 이후 해당 함수는 일반 함수 처럼 종료되는 것이 아니라 그 상태로 유지되게 된다. 
- 즉, 함수에서 사용된 local 변수나 instruction pointer 등과 같은 함수 내부에서 사용된 데이터들이 메모리에 그대로 유지되는 것이다.

다음은 제너레이터를 활용하여 피보나치 수열을 표현한 것이다.

```python
def fibo_gen(n):
    a = b = 1
    for i in range(n):
        yield a
        a, b = b, a+b
        
if __name__ == "__main__":
    f = fibo_gen(10)
    for i in range(10):
        print(next(f), end = '  ')
        
# result : 1  1  2  3  5  8  13  21  34  55
```

## Search
### linear search
- 리스트의 처음부터 끝까지 순차적으로 순회하며 데이터를 탐색하는 것이다.

```python
def linear_search(data, target):
    for idx in range(len(data)):
        if data[idx] == target:
            return idx
    return None

if __name__ == "__main__":
    data = [i for i in range(10)]
    target = 4
    idx = linear_search(data, target)
    
    if idx == None:
        print("{} 이 존재하지 않습니다.".format(target))
    else:
        print("찾는 데이터 {} 의 인덱스는 {} 입니다.".format(data[idx], idx+1))
        
# result : 찾는 데이터 4 의 인덱스는 4 입니다.
```

### binary search
- 정렬된 리스트를 받으며, 데이터의 반을 일정 기준을 통해 반복해서 제외시키기 때문에 단순 탐색의 최대 n번과 달리 최대 log2n번 만에 찾고자하는 데이터를 찾을 수 있다. 

```python
def binary_search(data, target):
    start = 0
    end = len(data) - 1

    while start <= end:
        mid = (start + end) // 2
        if target == data[mid]:
            return mid
        elif target < data[mid]:
            end = mid - 1
        else:
            start = mid + 1
    return None

if __name__ == "__main__":
    data = [i for i in range(10)]
    target = 4
    idx = binary_search(data, target)
    
    if idx == None:
        print("{} 이 존재하지 않습니다.".format(target))
    else:
        print("찾는 데이터 {} 의 인덱스는 {} 입니다.".format(data[idx], idx))
        
# result : 찾는 데이터 4 의 인덱스는 4 입니다.
```

## Big O
- 알고리즘이 얼마나 빠른지 말해준다.
- 최악의 경우를 성능 검사의 지표로 삼는다. = **최악의 경우에도 log n 의 성능을 보장한다.**
- 데이터가 n 개일 때 k 번 비교(start=end 일 때까지)
- n\*(1/2)^k=1 : 데이터 개수가 늘어날 때 증가율이 감소, 증가 경향이 필요한 것이지 정확한 수치는 필요하지 않다. 

```txt
T(n) = 3n^2 + 5n + 1 = 3n^2
O(n) = 단순탐색
O(log n) = binary search / binary search tree
O(n * log n) = 퀵 정렬
O(n^2) = 최악 bubble sort
```
