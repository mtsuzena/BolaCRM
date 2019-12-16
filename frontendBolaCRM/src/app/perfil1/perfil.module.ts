import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PerfilCadastrarComponent } from './perfil-cadastrar/perfil-cadastrar.component';
import { PerfilListarComponent } from './perfil-listar/perfil-listar.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [PerfilCadastrarComponent, PerfilListarComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ]
})
export class PerfilModule { }
 