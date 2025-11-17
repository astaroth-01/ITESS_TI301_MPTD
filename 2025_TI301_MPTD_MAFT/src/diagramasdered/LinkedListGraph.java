package diagramasdered;

public class LinkedListGraph<V, E> implements LinkedListGraphInterface<V, E> {
    private FactoryVertex<V> head;
    private boolean isDirected;
    private int vertexCount;
    
    public LinkedListGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.head = null;
        this.vertexCount = 0;
    }
    
    @Override
    public Vertex<V> addVertex(V v) {
        FactoryVertex<V> vertex = new FactoryVertex<>(v);
        
        if (head == null) {
            head = vertex;
        } else {
            FactoryVertex<V> actual = head;
            while (actual.nextVertex != null) {
                actual = actual.nextVertex;
            }
            actual.nextVertex = vertex;
        }
        
        vertexCount++;
        return vertex;
    }

    @Override
    public void addEdge(Vertex<V> v, Vertex<V> u, E w) {
        FactoryVertex<V> origin = validate(v);
        FactoryVertex<V> destination = validate(u);
        
        // Crear nueva arista
        FactoryEdge<E> edge = new FactoryEdge<>(v, u, w);
        
        // Agregar arista a la lista de adyacencia del vértice origen
        if (origin.adjacent == null) {
            origin.adjacent = edge;
        } else {
            FactoryEdge<E> currentEdge = (FactoryEdge<E>) origin.adjacent;
            while (currentEdge.nextEdge != null) {
                currentEdge = currentEdge.nextEdge;
            }
            currentEdge.nextEdge = edge;
        }
        
        // Si el grafo es no dirigido, agregar arista en ambos sentidos
        if (!isDirected) {
            FactoryEdge<E> reverseEdge = new FactoryEdge<>(u, v, w);
            
            if (destination.adjacent == null) {
                destination.adjacent = reverseEdge;
            } else {
                FactoryEdge<E> currentEdge = (FactoryEdge<E>) destination.adjacent;
                while (currentEdge.nextEdge != null) {
                    currentEdge = currentEdge.nextEdge;
                }
                currentEdge.nextEdge = reverseEdge;
            }
        }
    }
    
    public boolean getEdge(Vertex<V> v, Vertex<V> u) {
        FactoryVertex<V> origin = validate(v);
        FactoryVertex<V> destination = validate(u);
        
        // Buscar en la lista de adyacencia del vértice origen
        FactoryEdge<E> currentEdge = (FactoryEdge<E>) origin.adjacent;
        while (currentEdge != null) {
            if (currentEdge.u.equals(destination)) {
                return true;
            }
            currentEdge = currentEdge.nextEdge;
        }
        
        return false;
    }
    
    public E getEdgeWeight(Vertex<V> v, Vertex<V> u) {
        FactoryVertex<V> origin = validate(v);
        FactoryVertex<V> destination = validate(u);
        
        // Buscar en la lista de adyacencia del vértice origen
        FactoryEdge<E> currentEdge = (FactoryEdge<E>) origin.adjacent;
        while (currentEdge != null) {
            if (currentEdge.u.equals(destination)) {
                return currentEdge.weight;
            }
            currentEdge = currentEdge.nextEdge;
        }
        
        return null;
    }
    
    public void removeEdge(Vertex<V> v, Vertex<V> u) {
        FactoryVertex<V> origin = validate(v);
        FactoryVertex<V> destination = validate(u);
        
        // Remover arista de la lista de adyacencia del vértice origen
        removeEdgeFromVertex(origin, destination);
        
        // Si el grafo es no dirigido, remover también la arista inversa
        if (!isDirected) {
            removeEdgeFromVertex(destination, origin);
        }
    }
    
    private void removeEdgeFromVertex(FactoryVertex<V> origin, FactoryVertex<V> destination) {
        FactoryEdge<E> currentEdge = (FactoryEdge<E>) origin.adjacent;
        FactoryEdge<E> prevEdge = null;
        
        while (currentEdge != null) {
            if (currentEdge.u.equals(destination)) {
                if (prevEdge == null) {
                    origin.adjacent = currentEdge.nextEdge;
                } else {
                    prevEdge.nextEdge = currentEdge.nextEdge;
                }
                break;
            }
            prevEdge = currentEdge;
            currentEdge = currentEdge.nextEdge;
        }
    }
    
    public void removeVertex(Vertex<V> v) {
        FactoryVertex<V> vertexToRemove = validate(v);
        
        // Remover todas las aristas que apuntan a este vértice
        FactoryVertex<V> current = head;
        while (current != null) {
            if (!current.equals(vertexToRemove)) {
                removeEdgeFromVertex(current, vertexToRemove);
            }
            current = current.nextVertex;
        }
        
        // Remover el vértice de la lista de vértices
        if (head.equals(vertexToRemove)) {
            head = head.nextVertex;
        } else {
            current = head;
            while (current.nextVertex != null && !current.nextVertex.equals(vertexToRemove)) {
                current = current.nextVertex;
            }
            if (current.nextVertex != null) {
                current.nextVertex = current.nextVertex.nextVertex;
            }
        }
        
        vertexCount--;
    }
    
    public int getVertexCount() {
        return vertexCount;
    }
    
    public void printGraph() {
        FactoryVertex<V> currentVertex = head;
        while (currentVertex != null) {
            System.out.print("Vértice " + currentVertex.getElement() + ": ");
            
            FactoryEdge<E> currentEdge = (FactoryEdge<E>) currentVertex.adjacent;
            while (currentEdge != null) {
                System.out.print("-> " + currentEdge.u.getElement() + "(" + currentEdge.weight + ") ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            currentVertex = currentVertex.nextVertex;
        }
    }
    
    private FactoryVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof FactoryVertex)) throw new IllegalArgumentException("Vértice inválido");
        FactoryVertex<V> vertex = (FactoryVertex<V>) v;
        if (!vertex.validate(this)) throw new IllegalArgumentException("Vértice inválido");
        
        return vertex;
    }
    
    /***************************************************************************/
    private class FactoryVertex<V> implements Vertex<V> {
        private V v;
        private FactoryVertex<V> nextVertex;
        private Edge<E> adjacent;
        
        public FactoryVertex(V v) {
            this.v = v;
            this.nextVertex = null;
            this.adjacent = null;
        }
        
        @Override
        public V getElement() {
            return v;
        }
        
        public boolean validate(LinkedListGraphInterface<V, E> graph) {
            return (LinkedListGraph.this == graph);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            FactoryVertex<?> that = (FactoryVertex<?>) obj;
            return v.equals(that.v);
        }
        
        @Override
        public int hashCode() {
            return v.hashCode();
        }
    }
    /***************************************************************************/
    
    /***************************************************************************/
    private class FactoryEdge<E> implements Edge<E> {
        private Vertex<V> v;
        private Vertex<V> u;
        private E weight;
        private FactoryEdge<E> nextEdge;
        
        public FactoryEdge(Vertex<V> v, Vertex<V> u, E w) {
            this.v = v;
            this.u = u;
            this.weight = w;
            this.nextEdge = null;
        }
        
        public boolean validate(LinkedListGraphInterface<V, E> graph) {
            return (LinkedListGraph.this == graph);
        }

        @Override
        public E getElement() {
            return weight;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            FactoryEdge<?> that = (FactoryEdge<?>) obj;
            return v.equals(that.v) && u.equals(that.u) && weight.equals(that.weight);
        }
        
        @Override
        public int hashCode() {
            int result = v.hashCode();
            result = 31 * result + u.hashCode();
            result = 31 * result + weight.hashCode();
            return result;
        }
    }
    /***************************************************************************/

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Grafo ").append(isDirected ? "Dirigido" : "No Dirigido").append("\n");
    sb.append("Número de vértices: ").append(vertexCount).append("\n\n");
    
    FactoryVertex<V> currentVertex = head;
    while (currentVertex != null) {
        sb.append("Vértice '").append(currentVertex.getElement()).append("' : ");
        
        FactoryEdge<E> currentEdge = (FactoryEdge<E>) currentVertex.adjacent;
        if (currentEdge == null) {
            sb.append("Sin conexiones");
        } else {
            while (currentEdge != null) {
                sb.append("[")
                  .append(currentEdge.u.getElement())
                  .append(" (peso: ")
                  .append(currentEdge.weight)
                  .append(")]");
                
                if (currentEdge.nextEdge != null) {
                    sb.append(" -> ");
                }
                currentEdge = currentEdge.nextEdge;
            }
        }
        sb.append("\n");
        currentVertex = currentVertex.nextVertex;
    }
    
    return sb.toString();
}
}