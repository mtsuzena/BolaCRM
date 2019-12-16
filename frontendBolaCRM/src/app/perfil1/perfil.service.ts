import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Perfil } from './model/perfil.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  private PRIVATE_API = "http://localhost:8080/scp/private/";
  
  constructor(private http : HttpClient) { }
  list() : Observable<Perfil[]> {
    let options = {
      headers: new HttpHeaders().set('Authorization', "Bearer "+localStorage.getItem('token'))
    };
    
    return this.http.get<Perfil[]>(this.PRIVATE_API + "tipoperfil",options);
  }
  buscarPorID(guidTipoPerfil: string) : Observable<Perfil>  {
    let options = {
      headers: new HttpHeaders().set('Authorization', "Bearer "+localStorage.getItem('token'))
    };
    return this.http.get<Perfil>(this.PRIVATE_API + "tipoperfil/BuscarId/"+guidTipoPerfil,options);
  }

  salvar(perfil: Perfil) : Observable<any> {
    let options = {
      headers: new HttpHeaders().set('Authorization', "Bearer "+localStorage.getItem('token'))
    };
    return this.http.post<any>
      (this.PRIVATE_API + "tipoperfil/",perfil,options);
  }

}
