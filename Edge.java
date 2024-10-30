public class Edge {
    private int source; 
    private int destination; 
    public double energy;

    Edge(int source, int destination, int numPackets, double distance){ 
        this.source = source; 
        this.destination = destination; 
        this.energy = calculateEnergy(numPackets, distance);
    }

    public double calculateEnergy(int numPackets, double distance){ 
        double electricEnergy = 100 * Math.pow(10.0, -9.0);
        double amplifierEnergy = 100 * Math.pow(10.0, -12.0);

        double transmissionEnergy = (electricEnergy * numPackets * 3200) + (amplifierEnergy * numPackets * 3200 * Math.pow(distance, 2)); 
        double receivingEnergy = electricEnergy * numPackets * 3200; 
        
        return transmissionEnergy + receivingEnergy; 
    }

    public int getDestination(){
        return destination;
    }

    public double getEnergy(){ 
        return energy; 
    }

    public int getSource(){ 
        return source; 
    }
}
