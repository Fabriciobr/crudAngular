import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

//colocar no Enviroments
const baseUrl = 'https://crud-spring-741.herokuapp.com/api/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }


  get(id: any): Observable<Cliente> {
    return this.http.get<Cliente>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any):  Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  search(title: string, somenteAtivo: boolean): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${baseUrl}?nome=${title}&somenteAtivo=${somenteAtivo}`);
  }
}
