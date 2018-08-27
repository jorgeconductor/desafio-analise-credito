package com.jorge.conductor;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.jorge.conductor.service.FuncionarioService;
import com.jorge.conductor.service.PortadorService;

@SpringBootApplication
@EnableJms
public class ConductorServiceApp {
    
	//Template do Message Receiver
    public static JmsTemplate jmsTemplate;

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
    	// Configura o Listener do Message Receiver
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean 
    public MessageConverter jacksonJmsMessageConverter() {
    	// Configura o Message Receiver para o envio de sinais para o LOG quando necessário
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));   // Setar a timezone
    }

    public static void main(String[] args) {
    	// Inicia a aplicação
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConductorServiceApp.class, args);
        
        // Configura o bean do message receiver
        jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        
        // Carrega informações iniciais para os portadores no banco de dados
        PortadorService portadorService = applicationContext.getBean(PortadorService.class);
        portadorService.saveInitialBatch();
        
        // Carrega informações iniciais para os funcionarios no banco de dados
        FuncionarioService funcionarioService = applicationContext.getBean(FuncionarioService.class);
        funcionarioService.saveInitialBatch();

    }
}
