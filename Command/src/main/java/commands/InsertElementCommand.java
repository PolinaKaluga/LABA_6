package commands;


import collection.CollectionManager;
import collection.Ticket;
import collection.TicketFieldValidation;
import commands.Command;
import commands.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import workWithFile.TicketFieldsReader;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Команда, добавляющая элемент в коллекцию
 */
public class InsertElementCommand extends Command{
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;

    /**
     * Поле, хранящее ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */

    private TicketFieldsReader ticketFieldsReader;



    public InsertElementCommand(TicketFieldsReader ticketFieldsReader){
        super("insert");
        this.ticketFieldsReader=ticketFieldsReader;
    }
    /**
     * Конструктор класса, предназначенный для серверной части команды
     * @param collectionManager менеджер коллекции
     */
    public InsertElementCommand(CollectionManager collectionManager) {
        this.collectionManager=collectionManager;
    }

    /**
     * Метод, исполняющий команду. При запуске команды запрашивает ввод указанных полей. При успешном выполнении команды на стороне сервера высветится уведомление о добавлении элемента в коллекцию. В случае критической ошибки выполнение команды прерывается.
     *
     * @param invocationStatus режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
    @Override
    public void execute(String[] arguments, InvocationStatus invocationStatus, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationStatus.equals(InvocationStatus.CLIENT)) {
            result = new ArrayList<>();
            if (arguments.length > 1) {
                throw new CannotExecuteCommandException("Количество аргументов у данной команды должно быть не более 1.");
            }
            if (arguments.length == 1) {
                if (TicketFieldValidation.validate("id", arguments[0])) {
                    printStream.println("Введите значения полей для элемента коллекции:\n");
                    Ticket ticket = ticketFieldsReader.read(Integer.parseInt(arguments[0]));
                    super.result.add(Integer.parseInt(arguments[0])); //Integer id - result(0), dragon - result(1)
                    super.result.add(ticket);
                } else
                    throw new CannotExecuteCommandException("Введены невалидные аргументы: id = " + arguments[0]);
            }else{
                printStream.println("Введите значения полей для элемента коллекции:\n");
                Ticket ticket = ticketFieldsReader.read();
                super.result.add(ticket);
            }
        } else if (invocationStatus.equals(InvocationStatus.SERVER)) {
            if(result.size()==2) {
                Ticket ticket = (Ticket) this.getResult().get(1);
                collectionManager.insertWithId((Integer) this.getResult().get(0), ticket, printStream);
            }else{
                Ticket ticket = (Ticket) this.getResult().get(0);
                collectionManager.insert(ticket);
            }
        }
    }
    /**
     * Метод, возвращающий описание данной команды.
     * @return Описание данной команды.
     *
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "добавляет элемент с указанным ключом в качестве атрибута";
    }
}
