;;;
https://groups.csail.mit.edu/mac/ftpdir/scheme-7.4/doc-html/scheme_8.html

Define a Scheme function CROSS-MAP which takes a binary function
F and two lists XS and YS, and returns a list of the result of applying F
to each possible pair of elements with the first taken from XS and the
second from YS. (Order of elements in output is unspecified.)
Example:
(cross-map - '(100 200 300) '(4 3 2 1))
⇒ ( 96 97 98 99 196 197 198 199 296 297 298 299)
;;;

;;; solution 1

(define cross-map
    (lambda (f e1 e2)
      (append (cond ((and (number? e1) (number? e2)) (f e1 e2))
            
            ((and (list? e1) (list? e2))
            (cons (cross-map f (car e1) e2)
            (if (null? (cdr e1))
                null
                (cross-map f (cdr e1) e2))))
              
            ((and (number? e1) (list? e2))
            (cons (cross-map f e1 (car e2))
            (if (null? (cdr e2))
                null
                (cross-map f e1  (cdr e2)))))
      ))
))

(cross-map - '(100 200 300) '(4 3 2 1))

;;; Output:
;;; '((96 97 98 99) (196 197 198 199) (296 297 298 299))



;;; solution 2

(define cross-map-xxx
    (lambda (f e1 e2)
      (if (null? e2)
          '()
          (cons (f e1 (car e2)) (cross-map-xxx f e1 (cdr e2))))))

(define cross-map
    (lambda (f e1 e2)
      (if (null? e1)
           '()
           (cons (cross-map-xxx f (car e1) e2) (cross-map f (cdr e1) e2)))))

(cross-map - '(100 200 300) '(4 3 2 1))

;;; Output:
;;; '((96 97 98 99) (196 197 198 199) (296 297 298 299))