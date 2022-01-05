package adventofcode.y2021.day02;

public interface SubmarineCommandHandler {
    boolean canHandle(SubmarineCommand command);

    void handle(SubmarineCommand command, Submarine submarine);
    
    void undo(SubmarineCommand command, Submarine submarine);
}
