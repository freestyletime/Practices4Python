(define boat 7)
(define car 9)

!!! ---- version 1 ----
(define (check-b n b) (if (= (- n (* boat b)) 2) #t #f)) 
(define (check-c n c) (if (= (- n (* car c)) 1) #t #f))
(define (loop-find-b n b)
  (let  ([x (* boat b)])
        (cond ((>= x n) null)
              (else (if (check-b n b) b (loop-find-b n (+ b 1)))))))
(define (loop-find-c n c)
  (let  ([x (* car c)])
        (cond ((>= x n) null)
              (else (if (check-c n c) c (loop-find-c n (+ c 1)))))))

(define (loop-find-m m n)
  (if (> m n)
      null
      (let  ([x (loop-find-b m 1)]
             [y (loop-find-c m 1)])
            (cond ((null? x)(loop-find-m (+ m 1) n))
                  ((null? y)(loop-find-m (+ m 1) n))
                  (else (cons 
                          (list 
                            (string-append "M: " (format "~v" m))
                            (string-append "B: " (format "~v" x))
                            (string-append "C: " (format "~v" y)))
                          (loop-find-m (+ m 1) n)))))))
(define (how-much m n)
  (cond ((<= m n)(loop-find-m m n))
        ((> m n)(loop-find-m n m))
        (else "oops!")))

!!! ---- version 2 ----
(define boat 7)
(define car 9)
(define (check-b n) (if (= (remainder n boat) 2) #t #f))
(define (check-c n) (if (= (remainder n car) 1) #t #f))
(define (make-list m b c)
    (list   (string-append "M: " (format "~v" m))
            (string-append "B: " (format "~v" b))
            (string-append "C: " (format "~v" c))))

(define (find-common-list m n t)
    (let ([x (+ (* boat t) 2)])
    (cond ((< x m)(find-common-list m n (+ t 1)))
          ((and (<= x n) (check-c x)) (cons x (find-common-list m n (+ t 1))))
          ((> x n) null)
          (else (find-common-list m n (+ t 1))))))

(define (loop-find m n)
    (let*   ([z (if (< m 37) 37 m)]
             [x (quotient z boat)]
             [y (find-common-list m n x)])
            (if (null? y)
                null
                (for/list ([i y])
                    (make-list i (quotient i boat) (quotient i car))))))

(define (find-m m n)
  (cond ((< n 37) null)
        ((and (= m n) (and (check-b m) (check-c n)))
            (make-list m (quotient m boat) (quotient m car)))
        ((< m n) (loop-find m n))
        (else null)))

(define (how-much m n)
  (cond ((<= m n)(find-m m n))
        ((> m n)(find-m n m))
        (else "oops!")))

!!! ---- version 3 ----
(define (how-much m n)
  (for*/list ([i (in-inclusive-range (min m n) (max m n))]
              #:when (and (= (remainder i 9) 1) (= (remainder i 7) 2)))
        (list 
          (format "M: ~a" i) 
          (format "B: ~a" (quotient i 7)) 
          (format "C: ~a" (quotient i 9)))))

(how-much 1 100)
(how-much 1000 1100)
(how-much 10000 9950)
(how-much 1500 1600)
(how-much 2950 2950)
(how-much 608 840)