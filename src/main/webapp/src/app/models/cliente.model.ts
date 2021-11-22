import { Telefone } from "./telefone.model";

export class Cliente {
  
    id?: string;
    nome?: string;
    tipo?: string;
    cpfCnpj?: string;
    dataCadastro?: Date;
    ativo?: boolean;
    telefones: Telefone[] = new Array<Telefone>();

}
