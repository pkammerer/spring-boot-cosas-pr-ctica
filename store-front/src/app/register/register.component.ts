import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { User } from '@/_models/user';
import { UserService } from '@/_services/user.service';
import { Observable } from 'rxjs';

//import { AlertService, UserService, AuthenticationService } from '@/_services';

@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router, private userService: UserService
        
  /**      private authenticationService: AuthenticationService,
        private userService: UserService,
        private alertService: AlertService */
    ) { 
        // redirect to home if already logged in
   /**     if (this.authenticationService.currentUserValue) { 
            this.router.navigate(['/']);
        } */
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });

    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;
        this.loading = true
        // stop here if form is invalid
        if (this.registerForm.invalid) {
            this.loading = false
            return;
        }
        var newUser= new User()
        console.log(this.registerForm.value.firstName)
        newUser.firstName = this.registerForm.value.firstName;
        newUser.lastName = this.registerForm.value.lastName;
        newUser.username = this.registerForm.value.username;
        newUser.password = this.registerForm.value.password;
        newUser.rol = 'Cliente'
        this.userService.register(newUser).subscribe({
            next(position) {position}
        })
        this.router.navigate(['/login'])
        this.loading = false
        /**  this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });*/
    } 
}
