class Node(object):
    def __init__(self, key):
        self.key = key
        self.children = {}
        self.count = 0


class Trie(object):
    def __init__(self):
        self.head = Node(None)

    def insert(self, string):
        curr_node = self.head
        curr_node.count += 1

        for char in string:
            if char not in curr_node.children:
                curr_node.children[char] = Node(char)
            curr_node = curr_node.children[char]
            curr_node.count += 1

    def starts_with(self, prefix):
        curr_node = self.head
        for char in prefix:
            if char in curr_node.children:
                curr_node = curr_node.children[char]
            else:
                return 0

        return curr_node.count


def solution(words, queries):
    answer = []
    trie = {i: Trie() for i in range(1, 10001)}
    trie_opposite = {i: Trie() for i in range(1, 10001)}
    for word in words:
        trie[len(word)].insert(word)
        trie_opposite[len(word)].insert(word[::-1])

    for query in queries:
        if query == "?"*len(query):
            answer.append(trie[len(query)].head.count)
        elif query[0] == '?':
            prefix = query[::-1].split("?")[0]
            answer.append(trie_opposite[len(query)].starts_with(prefix))
        else:
            prefix = query.split("?")[0]
            answer.append(trie[len(query)].starts_with(prefix))

    print(answer)
    return answer


solution(["frodo", "front", "frost", "frozen", "frame", "kakao"], ["fro??", "????o", "fr???", "fro???", "pro?", "?????", "??", "f????", "???????"])