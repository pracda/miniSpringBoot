package Application;

import framework.Autowired;
import framework.Qualifier;
import framework.Service;

// Service implementations
@Service
public class SimpleGreetingService implements GreetingService {
    @Autowired
    @Qualifier("simplegreetingService")
     public GreetingService simplegreetingService;
    public void greet() {
        simplegreetingService.greet();
    }
}