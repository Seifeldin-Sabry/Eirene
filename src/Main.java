public class Main {
    public static void main(String[] args) {
        Temperatures temps = new Temperatures();
        TemperatureReader tempR = new TemperatureReader();
        // FINDING OUT YOUR COM PORTS
        //       final HashSet<String> ports = new HashSet<>(Arrays.asList(SerialPortList.getPortNames()));
        //        System.out.println(ports);
        // different port for mac/windows check
        tempR.connect();

    }
}
