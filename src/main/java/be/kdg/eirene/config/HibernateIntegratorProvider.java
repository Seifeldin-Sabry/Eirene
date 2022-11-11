package be.kdg.eirene.config;

import be.kdg.eirene.util.HibernateEventListenerIntegrator;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HibernateIntegratorProvider implements IntegratorProvider {

    @Override
    public List<Integrator> getIntegrators() {
        return List.of(HibernateEventListenerIntegrator.INSTANCE);
    }
}
