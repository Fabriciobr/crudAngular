import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteFormComponent } from './components/cliente-form/cliente-form.component';
import { ClienteListaComponent } from './components/cliente-lista/cliente-lista.component';
import { ClienteViewComponent } from './components/cliente-view/cliente-view.component';

const routes: Routes = [
  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  { path: 'clientes', component: ClienteListaComponent },
  { path: 'cliente', component: ClienteFormComponent },
  { path: 'cliente/:id', component: ClienteFormComponent },
  { path: 'cliente-view/:id', component: ClienteViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
