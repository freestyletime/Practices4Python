;;; create a program that can print the former number or the latter number
;;; from a function

(define com-pair
  (lambda (a b)(lambda (s)(s a b))))

(define pair-one
  (lambda (f)(f (lambda (a b) a))))

(define pair-two
  (lambda (f)(f (lambda (a b) b))))

(pair-one (com-pair 3 4))
(pair-two (com-pair 5 10))

;;; Output:

;;; 3
;;; 10
