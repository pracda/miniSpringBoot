package Application;

import framework.Autowired;
import framework.Qualifier;
import framework.Service;

// Service implementations
@Service
public class SimpleGreetingService implements IGreetingService {
    @Autowired
    @Qualifier("simplegreetingService")
     public IGreetingService simplegreetingService;
    public void greet() {
        simplegreetingService.greet();
    }
}