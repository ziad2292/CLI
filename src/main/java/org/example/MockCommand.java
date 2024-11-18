package org.example;

class MockCommand implements CommandInterface {
    private final String output;

    public MockCommand(String output) {
        this.output = output;
    }

    @Override
    public String execute(String... args) {
        return output;
    }
}
