package com.luiscarneiro.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luiscarneiro.cursomc.domain.Categoria;
import com.luiscarneiro.cursomc.domain.Cidade;
import com.luiscarneiro.cursomc.domain.Cliente;
import com.luiscarneiro.cursomc.domain.Endereco;
import com.luiscarneiro.cursomc.domain.Estado;
import com.luiscarneiro.cursomc.domain.ItemPedido;
import com.luiscarneiro.cursomc.domain.Pagamento;
import com.luiscarneiro.cursomc.domain.PagamentoComBoleto;
import com.luiscarneiro.cursomc.domain.PagamentoComCartao;
import com.luiscarneiro.cursomc.domain.Pedido;
import com.luiscarneiro.cursomc.domain.Produto;
import com.luiscarneiro.cursomc.domain.enums.EstadoPagamento;
import com.luiscarneiro.cursomc.domain.enums.TipoCliente;
import com.luiscarneiro.cursomc.repositories.CategoriaRepository;
import com.luiscarneiro.cursomc.repositories.CidadeRepository;
import com.luiscarneiro.cursomc.repositories.ClienteRepository;
import com.luiscarneiro.cursomc.repositories.EnderecoRepository;
import com.luiscarneiro.cursomc.repositories.EstadoRepository;
import com.luiscarneiro.cursomc.repositories.ItemPedidoRepository;
import com.luiscarneiro.cursomc.repositories.PagamentoRepository;
import com.luiscarneiro.cursomc.repositories.PedidoRepository;
import com.luiscarneiro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}