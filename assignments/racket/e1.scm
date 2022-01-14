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


;;; solution 3

(define cross-map
(lambda (f e1 e2)
  (for*/list ([x e1]
              [y e2])
              (f x y))))

(cross-map - '(100 200 300) '(4 3 2 1))

;;;Output:
;;;'(96 97 98 99 196 197 198 199 296 297 298 299)


;;; Define a Scheme function SCATTER-GATHER 
which takes two arguments, a list INDICES of indices and 
a list VALS of values, and returns a list of the same length 
as INDICES but with each value K replaced by the K-th element 
of VALS, or if that is out of range, by #f.
Example:
(scatter-gather '(0 1 4 1 1 7 2) '(a b c d e)) => (a b e b b #f c)
;;;

(define scatter-gather 
(lambda (e1 e2)
( let ([x (length e2)])
  (for/list ([i e1]) (if (< i x) (list-ref e2 i) #f)))))
(scatter-gather '(0 1 4 1 1 7 2) '(a b c d e))

;;;Output:
;;;'(a b e b b #f c)




(define maj3-filter
(lambda (f1 f2 f3 e) 
  (if (null? (car e))
        '()
        (let  ([x (car e)])
        (cond 
            ((and (f1 x) (f2 x)) (cons x (maj3-filter f1 f2 f3 (cdr e))))
            ((and (f1 x) (f3 x)) (cons x (maj3-filter f1 f2 f3 (cdr e))))
            ((and (f2 x) (f3 x)) (cons x (maj3-filter f1 f2 f3 (cdr e))))
            (else (list (maj3-filter f1 f2 f3 (cdr e))))
    )
))))