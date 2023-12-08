import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        //创建主题
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file path");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String inputFile = cmd.getOptionValue("input");
            String outputFile = cmd.getOptionValue("output");

            KWICSubject kwicSubject = new KWICSubject();
            Input input_ = new Input(inputFile);
            Shift shift = new Shift(input_.getLineTxt());
            Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
            Output output_ = new Output(alphabetizer.getKwicList(), outputFile);

            kwicSubject.addObserver(input_);
            kwicSubject.addObserver(shift);
            kwicSubject.addObserver(alphabetizer);
            kwicSubject.addObserver(output_);

            kwicSubject.startKWIC();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
    }
}