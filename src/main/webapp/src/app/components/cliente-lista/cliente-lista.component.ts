import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente.model';
import { ClienteService } from 'src/app/services/cliente.service';


@Component({
  selector: 'app-cliente-lista',
  templateUrl: './cliente-lista.component.html',
  styleUrls: ['./cliente-lista.component.css']
})
export class ClienteListaComponent implements OnInit {


  clientes?: Cliente[];

  somenteAtivo: boolean = false;
  nome: string = '';

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.search();
  }

  search(): void {
    this.clienteService.search(this.nome,this.somenteAtivo)
      .subscribe({
        next: (data) => {
          this.clientes = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  async deleteCliente(id: any) {
    await this.clienteService.delete(id)
    .subscribe({
      next: (data) => {
        this.search();
      },
      error: (e) => console.error(e)
    });
}

}
