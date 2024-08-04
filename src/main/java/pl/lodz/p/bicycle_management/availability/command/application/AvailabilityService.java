package pl.lodz.p.bicycle_management.availability.command.application;


import pl.lodz.p.bicycle_management.availability.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@Transactional
@RequiredArgsConstructor
public class AvailabilityService {

    private final BicycleAvailabilityRepository bicycleAvailabilityRepository;
//    private final AuthenticationService authenticationService;

    public BicycleAvailability create(final CreateCommand createCommand) {
        return bicycleAvailabilityRepository.save(new BicycleAvailability(BicycleNumber.of(createCommand.bicycleNumber())));
    }

    public void remove(final RemoveCommand removeCommand) {
        bicycleAvailabilityRepository.remove(BicycleNumber.of(removeCommand.bicycleNumber()));
    }

    public BicycleAvailability findByBicycleNumber(BicycleNumber bicycleNumber) {

        return bicycleAvailabilityRepository.findBy(bicycleNumber)
                .orElseThrow(BicycleNotFoundException::new);
    }

    public void lockBicycle(LockCommand lockCommand) {
        System.out.println("####### lock bicycle");
//        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleNumber(BicycleNumber.of(lockCommand.bicycleNumber()));
//        if (lockCommand.userId() == null) {
//            bicycleAvailability.lockFor(UserId.of(user.bicycleNumber()));
//        } else {
//            if (user.role() != UserRole.ADMIN) {
//                throw new MethodNotAllowedException();
//            }
//            bicycleAvailability.lockFor(UserId.of(lockCommand.userId()));
//        }
        bicycleAvailability.lockFor(UserId.of(lockCommand.userId()));
    }

    public Integer unlockBicycle(UnlockCommand unlockCommand) {
//        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleNumber(BicycleNumber.of(unlockCommand.bicycleNumber()));
        return bicycleAvailability.unlock();
    }

}