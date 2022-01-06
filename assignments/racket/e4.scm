!!! Q1
    !!! ---- verson 1 ----
(define find-sub-list
    (lambda 
      (ds l i)
      (if (= l i)
          null
          (cons (car ds) 
              (if (null? (cdr ds))
                null
                (find-sub-list (cdr ds) l (+ i 1)))))))

(define find-all-sub-list
    (lambda
      (ds l)
      (cons (find-sub-list ds l 0)
      (if (null? (cdr ds))
      null
      (find-all-sub-list (cdr ds) l)))))
      
(define search-c 
    (lambda 
      (xs ds t)
      (if (null? ds)
        null
        (if (equal? xs (car ds))
            (cons (if (null? t) null (car t))
                  (search-c xs (cdr ds) (car ds)))
            (append (search-c xs (cdr ds) (car ds)))))))
        
(define before-seq
    (lambda 
      (xs ys)
      (if (null? xs)
          ys
          (if (null? ys)
            null
            (let* ([x (length xs)]
                [ds (find-all-sub-list ys x)])
            (remove '() (search-c xs ds '())))))))

    ---- version 2 ----

(define recur (lambda (f x ds) (f x (car ds) (cdr ds))))
(define check-head
  (lambda   (e dds)
            (cond   ((null? e) #t)
                    ((eq? (car e) (car dds))(check-head (cdr e) (cdr dds)))
                    (else #f))))

(define glide-search
  (lambda   (e1 e2 ddds)
            (cond   ((null? ddds) null)
                    ((check-head e1 ddds) (cons e2 (recur glide-search e1 ddds)))
                    (else (recur glide-search e1 ddds)))))

(define before-seq
    (lambda (xs ys)
            (cond   ((or (null? xs) (null? ys)) ys)
                    (else (remove null (glide-search xs null ys))))))

(before-seq '(a b) '(x y z a b 1 2 3 4 a b c d a a b))
(before-seq '(a b) '(a b c d))
(before-seq '() '(j k l m n))
(before-seq '(t) '(a b t u v t t))

!!! Q2
(define list-x 
    (λ (s ds f1 f2) (list s (f1 (cadr ds)) (f2 (caddr ds)))))

(define ddx
  (λ (e)
     (cond 
      ((number? e) 0)
      ((eq? e 'x) 1)
      ((eq? (car e) '+) (list-x '+ e ddx ddx))
	  ((eq? (car e) '*)
		(list '+ 
		  (list-x '* e append ddx)
		  (list-x '* e ddx append)))
	  (else (error 'oops)))))

(ddx 'x)
(ddx '7)
(ddx '(+ x x))
(ddx '(+ (* x 3) x))

!!! Q3
(define f
  (λ (n)
    (cond ((= n 0) 1)
	      ((= n 1) 1)
		  (else (+ (f (- n 1)) (f (- n 2)))))))

(define c-ify2 (λ (f) (λ (c x y) (c (f x y)))))
(define c= (c-ify2 =))
(define c- (c-ify2 -))
(define c+ (c-ify2 +))
(define cf
  (λ (c n)
    (c= (λ (nez) 
    (if nez
        (c 1)
        (c= (λ (nezz)
                (if nezz
                    (c 1)
                    (c+ c
                    (c- (λ (nm1) (cf + nm1)) n 1)
                    (c- (λ (nm1) (cf + nm1)) n 2))))
        n 0)))
    n 1)))

(equal? (f 1) (cf (λ (x) x) 1))
(equal? (f 12) (cf (λ (x) x) 12))
(equal? (f 19) (cf (λ (x) x) 19))
(equal? (f 27) (cf (λ (x) x) 27))
(equal? (f 30) (cf (λ (x) x) 30))

!!! Q4
(define expand-and
  (lambda (e)
    (cond ((and (eq? (length e) 1) (eq? (car e) 'and)) '#t)
          ((and (eq? (length e) 2) (eq? (car e) 'and)) (cadr e))
          ((and (> (length e) 2) (eq? (car e) 'and)) (expand-and (cdr e)))
          (else (list 'if (car e) (expand-and (cons 'and (cdr e))) '#f)))))

(define expand-or
  (lambda (e)
    (cond ((and (eq? (length e) 1) (eq? (car e) 'or)) '#f)
          ((and (eq? (length e) 2) (eq? (car e) 'or)) (cadr e))
          ((and (> (length e) 2) (eq? (car e) 'or)) (cons 'cond (expand-or (cdr e))))
          (else (cons (list (car e) '=> '(λ (x) x))
                      (if (eq? (length (cdr e)) 1)
                        (list (list 'else (cadr e)))
                        (expand-or (cdr e))))))))

!!! (let ((x ONE)) (if x x (or TWO THREE)))

                             
(expand-or '(or))
(expand-or '(or ONE))
(expand-or '(or ONE TWO THREE))

(expand-and '(and))
(expand-and '(and ONE))
(expand-and '(and ONE TWO THREE FOUR))

!!! Q5
(define grovel-add
  (lambda (f s)
  (if (null? s)
      0
      (let ([x (car s)]
            [ds (cdr s)])
            (cond ((list? x)(+ (grovel-add f x) (grovel-add f ds)))
                  ((number? x)(if (f x) (+ x (grovel-add f ds)) (+ 0 (grovel-add f ds))))
                  (else (+ 0 (grovel-add f ds))))))))

(grovel-add (λ (x) #t) '(a b (5 x y (z 2))))
(grovel-add (λ (x) (< x 4)) '(a b (5 x y (z 2))))


