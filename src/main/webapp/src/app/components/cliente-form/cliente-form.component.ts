import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente.model';
import { Telefone } from 'src/app/models/telefone.model';
import { ClienteService } from 'src/app/services/cliente.service';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css']
})
export class ClienteFormComponent implements OnInit {


  cliente: Cliente = new Cliente();
  submitted = false;
  id: any = null;

  constructor(private clienteService: ClienteService,
    protected activatedRouter: ActivatedRoute,
    private toastr: ToastrService) { }

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
        error: (e) => {
          for (let i = 0; i < e.error.details.length; i++) {
            this.toastr.error(e.error.details[i])
          }
        }
      });
  }

  save(): void {

    this.clienteService.create(this.cliente)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
          this.toastr.success("Salvo com sucesso")
        },
        error: (e) => {
          console.log(e)
          for (let i = 0; i < e.error.details.length; i++) {
            this.toastr.error(e.error.details[i])
          }
        }
      });
  }


  addTelefone() {
    this.cliente.telefones.push(new Telefone());
  }

  removeTelefone(telefone: Telefone) {
    if (this.cliente.telefones.includes(telefone)) {
      this.cliente.telefones.splice(this.cliente.telefones.indexOf(telefone), 1);
    }
  }

}
