const password = document.querySelector('#password') as HTMLInputElement;
const confirmPassword = document.querySelector('#confirmPassword') as HTMLInputElement;

const eyeBtn = document.querySelector('#button-show-password') as HTMLButtonElement;
const eyeIcon = eyeBtn.querySelector('.bi-eye') as HTMLElement;
let hidden = true;

eyeBtn.addEventListener('click', () => {
    if (hidden) {
        password.setAttribute('type', 'text');
        if (confirmPassword != null)
            confirmPassword.setAttribute('type', 'text');
        eyeIcon.classList.remove('bi-eye');
        eyeIcon.classList.add('bi-eye-slash');
        hidden = false;
    } else {
        password.setAttribute('type', 'password');
        if (confirmPassword != null)
            confirmPassword.setAttribute('type', 'password');
        eyeIcon.classList.remove('bi-eye-slash');
        eyeIcon.classList.add('bi-eye');
        hidden = true;
    }
});
