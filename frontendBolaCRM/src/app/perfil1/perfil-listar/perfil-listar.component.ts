import { Component, OnInit } from '@angular/core';
import { PerfilService } from '../perfil.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Perfil } from '../model/perfil.model';

@Component({
  selector: 'app-perfil-listar',
  templateUrl: './perfil-listar.component.html',
  styleUrls: ['./perfil-listar.component.scss']
})
export class PerfilListarComponent implements OnInit {

  perfis : Perfil[];

  constructor(private perfilService : PerfilService,
    private router : Router) { }


  ngOnInit() {
    this.load();
  }
  load() {      
    this.perfilService.list().subscribe(
      perfis => {
        console.log(perfis);
        this.perfis = perfis
      }
    )

}

  edit(perfil: Perfil) {
    this.router.navigate(["/admin/tipoPerfil/"+perfil.guidTipoPerfil]); 
  }

  novo() {
    this.router.navigate(["/admin/tipoPerfil/novo"])
  }
}
