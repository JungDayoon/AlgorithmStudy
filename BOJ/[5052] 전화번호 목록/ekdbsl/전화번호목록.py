class Node(object):
    def __init__(self, key, data=None):
        self.key = key
        self.data = data
        self.children = {}


class Trie(object):
    def __init__(self):
        self.head = Node(None)

    def insert(self, string):
        curr_node = self.head

        for char in string:
            if char not in curr_node.children:
                curr_node.children[char] = Node(char)
            curr_node = curr_node.children[char]

            if curr_node.data is not None:
                return False

        curr_node.data = string
        return True


t = int(input())
for _ in range(t):
    trie = Trie()
    n = int(input())
    strList = [input() for _ in range(n)]
    strList = sorted(strList, key= lambda t: len(t))
    # print(strList)
    for string in strList:
        if not trie.insert(string):
            print("NO")
            break
    else:
        print("YES")