def solution(phone_book):
    answer = True
    phone_book_length = len(phone_book)

    phone_book.sort()
    for i in range(phone_book_length-1):
        if(phone_book[i+1].startswith(phone_book[i])):
            return False
    return answer

if __name__ == "__main__":
    phone = [["119", "97674223", "1195524421"], 
    ["123","456","789"],
    ["12","123","1235","567","88"]]

    for i in phone:
        print(solution(i))