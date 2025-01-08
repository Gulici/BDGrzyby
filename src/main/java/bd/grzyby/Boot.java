package bd.grzyby;

import bd.grzyby.model.entity.Gatunek;
import bd.grzyby.service.GatunekService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Boot implements CommandLineRunner {
    private final GatunekService gatunekService;

    public Boot(GatunekService gatunekService) {
        this.gatunekService = gatunekService;
    }

    @Override
    public void run(String... args) throws Exception {
        dodajGatunki();
    }

    private void dodajGatunki() {
        if(gatunekService.getAllGatunek().isEmpty()) {
            gatunekService.addGatunek(new Gatunek("Pieczarka",5.0));
        }
    }
}
