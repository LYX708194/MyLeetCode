package leetcode.time2021.three;

/**
 * @author lyx
 * @date 2021/3/26 14:40
 */
public class Trie208 {

    private TrieNode root;

    public Trie208() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            if (!node.containsKey(cur)){
                node.put(cur,new TrieNode());
            }
            node = node.get(cur);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String word){
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            if (node.containsKey(cur)){
                node = node.get(cur);
            }else{
                return null;
            }
        }
        return node;
    }

    private class TrieNode {

        private TrieNode[] links;

        private boolean isEnd;

        private final int R = 26;

        public TrieNode(){
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch){
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node){
            links[ch - 'a'] = node;
        }

        public void setEnd(){
            isEnd = true;
        }

        public boolean isEnd(){
            return isEnd;
        }

    }

}
