package br.ada.caixa.service;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.service.cliente.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteService clienteService;

    @Test
void registrarPFTest() {
    // given
    RegistrarClientePFRequestDto clienteDto = new RegistrarClientePFRequestDto();
    clienteDto.setCpf("12345678901");
    clienteDto.setCpf("Nome");
    clienteDto.setDataNascimento(LocalDate.now().minusYears(18));

    Cliente cliente = new Cliente();

    // when
    when(modelMapper.map(any(RegistrarClientePFRequestDto.class), eq(Cliente.class)))
            .thenReturn(cliente);
    when(clienteRepository.save(any(Cliente.class)))
            .thenReturn(cliente);
    clienteService.registrarPF(clienteDto);

    // then
    verify(clienteRepository, times(1)).save(any(Cliente.class));
}

@Test
void registrarPJTest() {
    // given
    RegistrarClientePJRequestDto clienteDto = new RegistrarClientePJRequestDto();
    clienteDto.setCnpj("12345678901234");
    clienteDto.setNomeFantasia("Nome Fantasia");
    clienteDto.setRazaoSocial("Razao Social");

    Cliente cliente = new Cliente();

    // when
    when(modelMapper.map(any(RegistrarClientePJRequestDto.class), eq(Cliente.class)))
            .thenReturn(cliente);
    when(clienteRepository.save(any(Cliente.class)))
            .thenReturn(cliente);
    clienteService.registrarPJ(clienteDto);

    // then
    verify(clienteRepository, times(1)).save(any(Cliente.class));
}

    @Test
    void listarTodosTest() {
        // when
        clienteService.listarTodos();

        // then
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void listarTodosByTipoTest() {
        // given
        TipoCliente tipoCliente = TipoCliente.PF;

        // when
        clienteService.listarTodos(tipoCliente);

        // then
        verify(clienteRepository, times(1)).findAllByTipo(tipoCliente);
    }
}