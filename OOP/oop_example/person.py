class Person:
    def __init__(self, name, age, money):
        self.name = name
        self.age = age
        self.money = money
        
    def give_other_money(self, other, how_much):
        self.money -= how_much
        other.money += how_much
        
    def __str__(self):
        return '''
        My name is {}.
        I am {} years old.
        I have {} won.'''.format(
            self.name,
            self.age,
            self.money,
        )

# 테스트 코드는 이 안에
# python person.py와 같이 실행시키는 메인 파일이 person이라면 아래 코드를 실행
if __name__ == "__main__":
    p1 = Person("bbungsang", 18, 5000)
    p2 = Person("bazzi", 22, 1000)
    print(p1)
    print(p2)

    p1.give_other_money(p2, 1000)
    print(p1)
    print(p2)