import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente.model';
import { Telefone } from 'src/app/models/telefone.model';
import { ClienteService } from 'src/app/services/cliente.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cliente-view',
  templateUrl: './cliente-view.component.html',
  styleUrls: ['./cliente-view.component.css']
})
export class ClienteViewComponent implements OnInit {


  cliente: Cliente = new Cliente();
  id: any = null;

  constructor(private clienteService: ClienteService,
    protected activatedRouter: ActivatedRoute,) { }

  ngOnInit(): void {
    this.id = this.activatedRouter.snapshot.paramMap.get('id');
    if(this.id){
      this.loadCliente();
    }
  }

  protected async loadCliente() {
    this.clienteService.get(this.id)
      .subscribe({
        next: (data) => {
          this.cliente = data;
        },
        error: (e) => console.error(e)
      });
  }


}
