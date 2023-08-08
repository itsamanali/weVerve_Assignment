class TrainBogie implements Comparable<TrainBogie> {
    private String station;
    private int distance;
    public TrainBogie() {}
    public TrainBogie(String station, int distance) {
        this.station = station;
        this.distance = distance;
    }


    public int getDistance() {
        return distance;
    }

    public String getStation() {
        return station;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public int compareTo(TrainBogie other) {
        return Integer.compare(other.distance, this.distance);
    }
}
