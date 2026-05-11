public class TreeNode implements Comparable<TreeNode>{
    private int valor;
    private TreeNode izquierda;
    private TreeNode derecha;
    private String character;

    public TreeNode(int value) {
    this.valor = value;
    this.setCharacter(null);
    this.izquierda= null;
    this.derecha= null;
    }
    public void setValor(int valor){
    this.valor = valor;
}
    public int getValor(){
        return this.valor;
    }
    public void setCharacter(String character) {
		this.character = character;
	}
    public boolean hasCharacter() {
		return this.character != null && !this.character.equals("");
    }
    public String getCharacter() {
		return character;
	}
    public TreeNode getDer(){
        return derecha;
    }
    public void setDer(TreeNode nodo){
        this.derecha=nodo;
    }
    public TreeNode getIzq(){
        return izquierda;
    }
    public void setIzq(TreeNode nodo){
        this.izquierda=nodo;
    }
    public int compareTo(TreeNode nodo){
        return this.valor-nodo.getValor();
    }
    public boolean noTieneHijos() {
		return this.getIzq() == null && this.getDer() == null;
    }
}