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

;;;
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in. Additionally, if the number is negative, return 0 (for languages that do have them).

Note: If the number is a multiple of both 3 and 5, only count it once.
;;;

(define solution 
  (lambda (n)
    (if (<= n 0)
        0
        (- (+ (solution2 0 3 n) (solution2 0 5 n)) (solution2 0 (* 3 5) n)))))
    
(define solution2
  (lambda (x y z)(
    if (< x z)
       (+ x (solution2 (+ x y) y z))
       0
    )))
    
(solution 10)
(solution 20)
(solution 200)


;;; Output:

;;; 23
;;; 78
;;; 9168