/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function tipoEntrada(){
    let entrada = document.getElementById('campo');
    let change = entrada.value;
    
    if(change == "birth") {
        document.getElementById('texto').style.display = 'none';
        document.getElementById('fecha').style.display = 'block';
        document.getElementById('contraseña').style.display = 'none';
        document.getElementById('opcion').style.display = 'none';
    
        document.getElementById('texto').required = false;
        document.getElementById('fecha').required = true;
        document.getElementById('contraseña').required = false;
    
    } else if (change == "password") {
        document.getElementById('texto').style.display = 'none';
        document.getElementById('fecha').style.display = 'none';
        document.getElementById('contraseña').style.display = 'block';
        document.getElementById('opcion').style.display = 'none';
        
        document.getElementById('texto').required = false;
        document.getElementById('fecha').required = false;
        document.getElementById('contraseña').required = true;
        
    } else if (change == "sexo") {
        
        document.getElementById('texto').style.display = 'none';
        document.getElementById('fecha').style.display = 'none';
        document.getElementById('contraseña').style.display = 'none';
        document.getElementById('opcion').style.display = 'block';
        
        document.getElementById('texto').required = false;
        document.getElementById('fecha').required = false;
        document.getElementById('contraseña').required = false;
        
    } else {
        document.getElementById('texto').style.display = 'block';
        document.getElementById('fecha').style.display = 'none';
        document.getElementById('contraseña').style.display = 'none';
        document.getElementById('opcion').style.display = 'none';
        
        document.getElementById('texto').required = true;
        document.getElementById('fecha').required = false;
        document.getElementById('contraseña').required = false;
        
    }
    
} 