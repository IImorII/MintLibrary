//package controller.command.action.account;
//
//import controller.command.Command;
//import controller.command.CommandRequest;
//import controller.command.CommandResponse;
//import controller.command.ParameterDestination;
//import exception.CommandException;
//
//import java.util.Optional;
//
//import static controller.command.ControllerDestination.MAIN;
//
//public class LoginCommand implements Command {
//    @Override
//    public CommandResponse execute(CommandRequest request) throws CommandException {
//        try {
//            final String userName = request.getStringParameter(ParameterDestination.USER_NAME.getValue());
//            final String userPassword = request.getStringParameter(ParameterDestination.USER_PASSWORD.getValue());
//            final Optional<AccountDto> userOptional = accountService.login(userName, userPassword);
//            if (userOptional.isPresent()) {
//                final AccountDto user = userOptional.get();
//                request.setSessionAttribute(ParameterDestination.USER_ID.getValue(), user.getId());
//                request.setSessionAttribute(ParameterDestination.USER_NAME.getValue(), userName);
//                request.setSessionAttribute(ParameterDestination.USER_ROLE.getValue(), user.getRole());
//                request.setSessionAttribute(ParameterDestination.USER_BALANCE.getValue(), user.getBalance());
//                return() -> MAIN;
//            } else {
//                request.setAttribute(ParameterDestination.ERROR.getParameter(), "Incorrect login or password!");
//                return() -> MAIN;
//            }
//        } catch (Exception ex) {
//            throw new CommandException(ex.getMessage());
//        }
//    }
//}
