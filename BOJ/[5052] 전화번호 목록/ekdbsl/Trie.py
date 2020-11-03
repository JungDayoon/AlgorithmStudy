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

        curr_node.data = string
        return True

    def search(self, string):
        curr_node = self.head

        for char in string:
            if char in curr_node.children:
                curr_node = curr_node.children[char]
            else:
                return False

        if curr_node.data is not None:
            return True

    def starts_with(self, prefix):
        curr_node = self.head
        result = []
        subTrie = None

        for char in prefix:
            if char in curr_node.children:
                curr_node = curr_node.children[char]
                subTrie = curr_node
            else:
                return None

        queue = list(subTrie.children.values())
        # print(queue)

        while queue:
            curr = queue.pop(0)
            if curr.data is not None:
                result.append(curr.data)

            queue += list(curr.children.values())

        return result


t = int(input())
for _ in range(t):
    trie = Trie()
    n = int(input())
    strList = [input() for _ in range(n)]
    strList = sorted(strList, key= lambda t: len(t))
    prefix = input()
    # print(strList)
    for string in strList:
        trie.insert(string)
    #     if not trie.insert(string):
    #         print("NO")
    #         break
    # else:
    #     print("YES")

    print(trie.starts_with(prefix))