package br.ada.caixa.service;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.TipoConta;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import br.ada.caixa.service.conta.ContaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ContaService contaService;

    @Test
    void abrirContaPoupancaTest() {
        // given
        String cpf = "12345678901";
        Cliente cliente = new Cliente();
        Conta conta = new Conta();
        conta.setTipo(TipoConta.CONTA_POUPANCA);
        conta.setCliente(cliente);
        conta.setSaldo(BigDecimal.ZERO);

        // when
        when(clienteRepository.findByDocumento(cpf))
                .thenReturn(Optional.of(cliente));
        when(contaRepository.save(any(Conta.class)))
                .thenReturn(conta);
        Conta result = contaService.abrirContaPoupanca(cpf);

        // then
        assertEquals(conta, result);
        verify(clienteRepository, times(1)).findByDocumento(cpf);
        verify(contaRepository, times(1)).save(any(Conta.class));
    }
}