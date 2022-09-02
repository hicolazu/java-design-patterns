package SOLID;

// A Class should not be forced to depend on methods it does not use

class Document {

}

interface Machine {
    void print(Document document);
    void fax(Document document);
    void scan(Document document);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document document) {
        // used
    }

    @Override
    public void fax(Document document) {
        // used
    }

    @Override
    public void scan(Document document) {
        // used
    }
}

class OldFashionPrinter implements Machine {

    @Override
    public void print(Document document) {
        // used
    }

    @Override
    public void fax(Document document) {
        // not used
    }

    @Override
    public void scan(Document document) {
        // not used
    }
}

// Segregate the Machine Interface into More Specific Interfaces
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Faxer {
    void fax();
}

class BetterMultiFunctionPrinter implements Printer, Scanner, Faxer {

    @Override
    public void print() {
        //
    }

    @Override
    public void scan() {
        //
    }

    @Override
    public void fax() {
        //
    }
}

class BetterOldPrinter implements Printer {

    @Override
    public void print() {
        //
    }
}

// YAGNI = You Ain't Going to Need It

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {

    }
}
