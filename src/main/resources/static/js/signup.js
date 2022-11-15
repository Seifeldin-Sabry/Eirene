const password = document.querySelector('#password');

const eyeBtn = document.querySelector('#button-show-password');
const eyeIcon = eyeBtn.querySelector('.bi-eye');
let hidden = true;

eyeBtn.addEventListener('click', () => {
    if (hidden) {
        password.setAttribute('type', 'text');
        eyeIcon.classList.remove('bi-eye');
        eyeIcon.classList.add('bi-eye-slash');
        hidden = false;
    } else {
        password.setAttribute('type', 'password');
        eyeIcon.classList.remove('bi-eye-slash');
        eyeIcon.classList.add('bi-eye');
        hidden = true;
    }
});

